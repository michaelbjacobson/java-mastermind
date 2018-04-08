node {
    def app

    stage('Clone repo') {
        sh 'cd $JENKINS_HOME; ls -alt'
        sh 'cd ~; ls -alt'
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
}