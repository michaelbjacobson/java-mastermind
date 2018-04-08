node {
    def app

    stage('Clone repo') {
        sh 'docker logout https://445579089480.dkr.ecr.us-east-1.amazonaws.com'
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
        sh 'cd $JENKINS_HOME; rm -rf .docker; mkdir .docker; cd .docker; touch config.json; echo {"auths":{}} >> config.json'
        sh 'cd ~; rm -rf .docker; rm -rf .dockercfg; mkdir .docker'

        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-credentials") {
            sh 'cp $JENKINS_HOME/.docker/config.json ~/.docker/config.json'
            sh 'echo "}" >> ~/.docker/config.json'
            sh 'cat ~/.docker/config.json'
            app.push('latest')
        }
    }
}