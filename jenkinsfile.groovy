pipeline {
    agent any

    stages {
        stage('Build and Push Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh 'docker build -t amoghbari/jenkins-assignment:v2 .'
                    
                    // Push Docker image to private registry
                    sh 'docker login -u amoghbari -p A@mogh1993'
                    sh 'docker push amoghbari/jenkins-assignment:v2'
                }
            }
        }


    stage('Deploy to Kubernetes') {
             steps {
                 script {
                     sh 'sudo -u sigmoid minikube start'
                     sh 'sudo -u sigmoid kubectl apply -f kubernetes-deployment.yaml'
                 }
             }
         }

         stage('Listing'){
            steps{
                script{
                    sh 'docker images'
                    sh 'sudo -u sigmoid kubectl get pods'
                    sh 'sudo -u sigmoid kubectl get deployments'
                }
            }
         }

        // Add more stages for deployment and verification as needed
    }

    post {
        success {
            emailext subject: 'Pipeline Success',
            body: 'The Pipeline was Successful.',
            to: 'amogh.pb@sigmoidanalytics.com'
        }
        failure {
            emailext subject: 'Pipeline Failure',
            body: 'The Pipeline Failed.',
            to: 'amogh.pb@sigmoidanalytics.com'
        }
    }
}
