#!/usr/bin/env groovy

pipeline {
	agent any

	tools {
        maven 'Default'
        jdk 'Default'
     }

	stages {
		stage('Initialize') {
			steps {
				echo 'Initializing....'
			}
		}
		stage('Style checking') {
		    steps {
		        echo 'Style Checking...'
		        checkstyle canComputeNew: false, canRunOnFailed: true, defaultEncoding: '', healthy: '100', pattern: '', unHealthy: '100'
		    }
		}
		stage('Compile & Test') {
			steps {
				echo 'running Maven build'
				sh 'mvn clean package site'
			}
			post {
				success {
					echo 'done.'
					junit '**/target/surefire-reports/*.xml'
				}
			}
		}
		stage('System Tests') {
			agent {
                docker {
                    image 'hsqldb'
                    args  '-d -p 9001:9001'
                }
            }

			steps {
				echo 'running Docker'

			}
		}
	}
	post {
            always {
                archiveArtifacts artifacts: '**/target/**/*', onlyIfSuccessful: true
            }
    }
}
