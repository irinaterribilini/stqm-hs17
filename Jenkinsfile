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
}
