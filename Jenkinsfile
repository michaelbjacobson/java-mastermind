node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        sh 'whoami; cd ../..; pwd; ls -alt | grep docker; cat .dockercfg'
        sh 'cd ../..; rm .dockercfg; mkdir .docker; cd .docker; touch config.json'
        app = docker.build("mastermind")
    }

    stage('Push image') {
        withDockerRegistry([credentialsId: 'ecr:us-east-1:aws-credentials', url: 'https://445579089480.dkr.ecr.us-east-1.amazonaws.com']) {
        }
    }
}