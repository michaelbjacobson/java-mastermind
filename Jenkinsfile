node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build Docker image') {
        app = docker.build("mastermind")
    }

    stage('Run unit tests') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Push image') {
        docker.withRegistry('https://445579089480.dkr.ecr.eu-west-2.amazonaws.com', 'ecr:eu-west-2:ecr-credentials') {
            app.push('latest')
        }
    }

}