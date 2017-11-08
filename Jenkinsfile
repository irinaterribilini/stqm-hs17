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
				}
			}
		}
		stage('System Tests') {
			steps {
				echo 'running Docker'
			}
		}
		stage('Finishing build') {
		    steps {
		        echo 'Saving Artifacts...'
                archiveArtifacts artifacts: '**/target/checkstyle-results.xml, **/target/MRSfx*, **/target/site/**/*', onlyIfSuccessful: true
		    }
		}
	}
}
