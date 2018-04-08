node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
        docker.image('mesosphere/aws-cli').inside {
            sh 'aws help'
        }
    }
}