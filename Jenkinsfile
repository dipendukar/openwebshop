pipeline {
    agent any

    tools {
        maven 'Maven 3.9.12'  // Use the name you set in Global Tool Configuration
        jdk 'Java25'         // Optional: specify Java if needed
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
