pipeline{
  agent 
  any parameters { 
    string(name: 'new_build_number', defaultValue: '', description: 'Enter New Build Number') 
    string(name: 'Triggered_by', defaultValue: '', description: 'Enter your full name') 
  } 
  stages{
    stage('Send Deployment Failure Alert'){
      steps{ 
        script{ 
          def emailSubject = """Failure: Request For Deployment Of Latest Trunk build On Performance Preval (DFW) Environment""" 
          def emailBody = """ Hi All, <p>Unfortunately,</p> <br> The Deployment failed in "${params.jobname} Pipeline. Please find the details below:</p> 
          <p>Job: ${env.JOB_NAME} </p> 
          <br> 
          <p>New Build Number: ${params.new_build_number}</p> 
          <br> 
          <p>URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p> 
          <br> 
          <br>
          Please refer to more details on the Wiki. <small>- Automated E-Mail from Riskonnect Jenkins</small> 
          """ 
          emailext(
            to: "kartik.gupta@riskonnect.com,manjunath.pm@riskonnect.com,srinath.shetty@riskonnect.com,haroon.rasheed@riskonnect.com",
            subject: emailSubject,
            body: emailBody, 
            mimeType: 
            'text/html' 
          ) 
        } 
      } 
    } 
  } 
}
