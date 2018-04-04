node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build Docker image') {
        app = docker.build("michaeljacobson/mastermind")
    }

    stage('Setup environment for integration tests') {
        app.inside {
            sh 'chmod +x ./install_firefox.sh'
            sh './install_firefox.sh'
            sh 'chmod +x ./install_geckodriver.sh'
            sh './install_geckodriver.sh'
        }
    }

    stage('Run unit tests') {
        app.inside {
            sh 'mvn test'
        }
    }
}