pipeline {
    agent {
        docker {
                image 'maven:3-alpine'
                label 'my-defined-label'
                args  '-v /tmp:/tmp'
            }
    }
    stages {
        stage('Build') {
            steps {
                app = docker.build('mastermind')
            }
        }
        stage('Test') {
            steps {
                app.inside {
                    sh 'mvn test'
                }
            }
        }
        stage('Deploy') {
            steps {
                docker.withRegistry('https://445579089480.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:aws-credentials') {
                    app.push('latest')
                }
            }
        }
    }
}