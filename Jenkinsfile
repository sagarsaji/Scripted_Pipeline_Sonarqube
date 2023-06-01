pipeline {
    agent any

    stages {
        stage('Dev') {
            steps {
                git branch: 'main', credentialsId: 'ad63f2f4-0502-4bb9-bd0b-e2d047f54da8', url: 'https://github.com/sagarsaji/Module_3_answer.git'
                bat 'mvn clean install'
            }
        }

        stage('UAT') {
                    steps {
                        git branch: 'main', credentialsId: 'ad63f2f4-0502-4bb9-bd0b-e2d047f54da8', url: 'https://github.com/sagarsaji/Module_3_answer.git'
                        bat 'mvn clean test'
                    }
                }

         stage('SonarQube Analysis') {
                    steps {
                        withSonarQubeEnv('sonarserver') {
                            bat 'mvn clean install sonar:sonar'
                        }
                    }
                }

        stage('Prod') {
            steps {
                git branch: 'main', credentialsId: 'ad63f2f4-0502-4bb9-bd0b-e2d047f54da8', url: 'https://github.com/sagarsaji/Module_3_answer.git'
                bat 'mvn clean install'
            }
        }
    }
}
