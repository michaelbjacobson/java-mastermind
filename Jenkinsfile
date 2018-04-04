node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build Docker image') {
        app = docker.build("michaeljacobson/mastermind")
    }


    stage('Run unit tests') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Test') {
        sh 'pwd'
        sh 'ls'
    }

}