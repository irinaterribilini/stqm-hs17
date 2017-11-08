#!/usr/bin/env groovy

pipeline {
	agent any

    docker {
        image 'blacklabelops:hsqldb'
        label 'HSQLDB'
        args  '-d -p 9001:9001'
    }

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
				}
			}
		}
		stage('System Tests') {
			steps {
				echo 'running Docker'
			}
		}
	}
	post {
            always {
                archiveArtifacts artifacts: '**/target/**/*', onlyIfSuccessful: true
                junit '**/target/surefire-reports/*.xml'
            }
    }
}
