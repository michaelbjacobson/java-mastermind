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
        echo "Hello, World!"
        echo credentials(ecr-credentials)
    }

}