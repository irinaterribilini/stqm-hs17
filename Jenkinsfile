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
		        [$class: 'hudson.plugins.checkstyle.CheckStylePublisher', pattern: '**/target/checkstyle-result.xml', unstableTotalAll:'0',unhealthy:'100', healthy:'100']
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
