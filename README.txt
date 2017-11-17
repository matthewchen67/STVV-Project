FIRST:
NOTE: Eclipse Kepler Standard with Java EE was used
NOTE: Install Apache Maven (https://maven.apache.org/download.cgi)

Import the extended version of pitest as a maven project into Eclipse

Right click the pom.xml file in the "pitest" folder and Run as Maven Build

Enter into Goal field: "clean install -DskipTests"


To specify which mutators are going to be used:
*Must be pasted into the project pom.xml that is going to be mutated*

<build>
  	<plugins>
  		<plugin>
    		<groupId>org.pitest</groupId>
    		<artifactId>pitest-maven</artifactId>
    		<version>1.2.4</version>
    		<configuration>
        		<mutators>
        		        <mutator>CRCR1</mutator>
        			<mutator>CRCR2</mutator>
        			<mutator>CRCR3</mutator>
        			<mutator>CRCR4</mutator>
        			<mutator>CRCR5</mutator>
        			<mutator>ABS</mutator>
        			<mutator>UOI1</mutator>
        			<mutator>UOI2</mutator>
        			<mutator>UOI3</mutator>
        			<mutator>AOR_OBBN1</mutator>
        			<mutator>AOR_OBBN2</mutator>
        			<mutator>AOR_OBBN3</mutator>
        			<mutator>AOR_OBBN4</mutator>
        			<mutator>ROR1</mutator>
        			<mutator>ROR2</mutator>
        			<mutator>ROR3</mutator>
        			<mutator>AOD1</mutator>
        			<mutator>AOD2</mutator>
        		</mutators>
    		</configuration>
		</plugin>
	</plugins>
</build>

To Run the mutation tests:
Open Command Prompt
Relocate to the project directory (cd /*test_project*)
Run command (mvn org.pitest:pitest-maven:mutationCoverage)
Mutation Reports will be located in target folder under pit-reports
