pipeline {
    agent any

    tools {
        maven 'Maven 3.9.12'  // Use the name you set in Global Tool Configuration
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
    }
}
