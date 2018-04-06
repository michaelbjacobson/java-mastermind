node {

    docker.image('node:7-alpine').inside {
        def app

        stage('Clone repo') {
            checkout scm
        }

        stage('Build image') {
            app = docker.build("mastermind")
        }

        stage('Run unit tests') {
            app.inside {
                sh 'mvn test'
            }
        }

        stage('Push image') {
            sh 'aws help'
            docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-creds") {
                app.push("latest")
            }
        }
    }
}