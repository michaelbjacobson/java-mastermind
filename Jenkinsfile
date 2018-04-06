pipeline {

    agent {
        docker { image 'node:7-alpine' }
    }

    stages {
        def app

        stage('Clone repo') {
            steps {
                checkout scm
            }
        }

        stage('Build image') {
            steps {
                app = docker.build("mastermind")
            }
        }

        stage('Run unit tests') {
            steps {
                app.inside {
                    sh 'mvn test'
                }
            }
        }

        stage('Push image') {
            steps {
                docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-creds") {
                    app.push("latest")
                }
            }
        }
    }
}