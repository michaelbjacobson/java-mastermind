node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build Docker image') {
        app = docker.build("michaeljacobson/mastermind")
    }


    stage('Run unit tests (inside)') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Run unit tests (outside)') {
        sh 'mvn test'
    }

}