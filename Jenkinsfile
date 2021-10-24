properties([
  parameters([
    string(name: 'PLUG_KEY', defaultValue: ''),
//    string(name: 'SERVERID', defaultValue: 'BQX9-6FJT-S0YH-ZCO1'),
    choice(name: 'APP', choices: ['Jira', 'Confluence', 'Bitbucket'])
  ])
])

pipeline {
	agent {
		docker {
			image 'php:8.0.5'
			args '-u 0'
		}
	}
	stages {
		stage('Get key for Jira') {
			when {
				expression { params.APP == 'Jira' }
			}
			steps {
				sh 'echo "Jira plugin"'
				//sh 'printenv'
				sh '''#!/bin/bash
				sed -i "s/com.gliffy.integration.jira/"$PLUG_KEY"/g" license_key_gliffy_jira.txt
				sed -i "s/ServerID=BQX9-6FJT-S0YH-ZCO1/ServerID=BQX9-6FJT-S0YH-ZCO1/g" license_key_gliffy_jira.txt
				cat license_key_gliffy_jira.txt
				chmod +x atlassian-keygen.php
				./atlassian-keygen.php -e license_key_gliffy_jira.txt
				'''
			}
		}
		stage('Get key for Confluence') {
			when {
				expression { params.APP == 'Confluence' }
			}
			steps {
				sh 'echo "Confluence plugin"'
				//sh 'printenv'
				sh '''#!/bin/bash
				sed -i "s/com.gliffy.integration.jira/"$PLUG_KEY"/g" license_key_gliffy_confluence.txt
				sed -i "s/ServerID=BQX9-6FJT-S0YH-ZCO1/ServerID=B1Y3-1R40-C2Z4-516N/g" license_key_gliffy_confluence.txt
				cat license_key_gliffy_confluence.txt
				chmod +x atlassian-keygen.php
				./atlassian-keygen.php -e license_key_gliffy_confluence.txt
				'''
			}
		}
		stage('Get key for Bitbucket') {
			when {
				expression { params.APP == 'Bitbucket' }
			}
			steps {
				sh 'echo "Bitbucket plugin"'
				//sh 'printenv'
				sh '''#!/bin/bash
				sed -i "s/com.gliffy.integration.jira/"$PLUG_KEY"/g" license_key_gliffy_jira.txt
				sed -i "s/ServerID=BQX9-6FJT-S0YH-ZCO1/ServerID=BT1U-OG2F-97JH-Y6YO/g" license_key_gliffy_jira.txt
				cat license_key_gliffy_jira.txt
				chmod +x atlassian-keygen.php
				./atlassian-keygen.php -e license_key_gliffy_jira.txt
				'''
			}
		}
	}
}