podTemplate(containers: [
  containerTemplate(name: 'maven', image: 'maven:3-jdk-11', ttyEnabled: true, command: 'cat')
  ], volumes: [
  persistentVolumeClaim(mountPath: '/root/.m2/repository', claimName: 'jenkins-maven-pv-claim', readOnly: false)
  ]) {

  node(POD_LABEL) {
    stage('build') {
      git 'http://192.168.8.11:30280/sivieri/advent-of-code-2020.git'
      container('maven') {
          sh 'java -version'
          sh 'mvn -B clean package'
      }
      junit '**/target/surefire-reports/TEST-*.xml'
    }
  }
}