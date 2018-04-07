node {
    def app

    stage('Clone repo') {
        sh 'printenv'
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        sh 'cd ../..; mkdir .docker; cd .docker; touch config.json; pwd; ls'
        withDockerRegistry([credentialsId: 'ecr:us-east-1:aws-credentials', url: 'https://445579089480.dkr.ecr.us-east-1.amazonaws.com']) {
        }
    }
}