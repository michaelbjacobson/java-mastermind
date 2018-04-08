node {
    def app

    stage('Clone repo') {
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
        stage('Push image') {
            sh 'cd $JENKINS_HOME; rm -rf .docker; mkdir .docker; cd .docker; touch config.json; echo {"auths":{}} >> config.json'
            sh 'cd ~; rm -rf .docker; rm -rf .dockercfg; mkdir .docker'

            docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-credentials") {
                sh 'cp $JENKINS_HOME/.docker/config.json ~/.docker/config.json'
                sh 'sed -e "s#https://##g" -i ~/.docker/config.json'

                app.push('latest')
            }
        }
    }
}