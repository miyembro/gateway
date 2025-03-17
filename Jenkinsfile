pipeline {
    agent any

    environment {
        // You must set the following environment variables
        // ORGANIZATION_NAME
        // DOCKERHUB_USERNAME (it doesn't matter if you don't have one)

        SERVICE_NAME = "gateway"  // Replace with your service name
        IMAGE_TAG = "${SERVICE_NAME}-${ORGANIZATION_NAME}:latest"
        REPOSITORY_TAG = "${DOCKERHUB_USERNAME}/${SERVICE_NAME}-${ORGANIZATION_NAME}:latest"
    }

    stages {
        stage('Preparation') {
            steps {
                cleanWs()  // Clean the workspace
                git credentialsId: 'GitHub', url: "https://github.com/${ORGANIZATION_NAME}/${SERVICE_NAME}", branch: 'main'  // Clone the repository
                sh 'chmod +x gradlew'  // Add execute permission to gradlew
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'  // Build the project using Gradle
            }
        }

        stage('Build and Push Image') {
            steps {
                 sh 'docker --version'
                sh 'docker image build -t ${IMAGE_TAG} .'  // Build the Docker image
                sh 'docker push ${REPOSITORY_TAG}'  // Push the Docker image to the registry
            }
        }

        stage('Deploy to Cluster') {
            steps {
                sh 'envsubst < ${WORKSPACE}/deploy.yaml | kubectl apply -f -'  // Deploy to Kubernetes
            }
        }
    }
}