node {
    def app

    stage('Clone repo') {
        sh 'pwd'
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mj/mastermind")
    }

    stage('Push image') {
        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-creds") {
            sh '~/.docker/cat config.json'
        }
    }
}