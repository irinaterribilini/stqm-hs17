#!/usr/bin/env groovy

pipeline {
	agent any
	
	stages {
		stage('Initialize') {
			steps {
				echo 'Initializing....'
			}
		}
		stage('Compile & Test') {
		    git url: 'https://github.com/irinaterribilini/stqm-hs17'

		    withMaven {
		        maven: 'Default'
		        mavenLocalRepo: '.repository')
		    }

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
}
