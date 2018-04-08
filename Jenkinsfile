node {
    def app

    stage('Clone repo') {
        sh 'cat $JENKINS_HOME/.docker/config.json'
        sh 'cat $HOME/.docker/config.json'
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-credentials") {
            sh 'cat $JENKINS_HOME/.docker/config.json'
            sh 'cat $HOME/.docker/config.json'
        }
    }
}