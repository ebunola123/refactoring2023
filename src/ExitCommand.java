import java.io.File;
import javax.swing.JOptionPane;

public class ExitCommand{

	public void exitApp(File file) {
		EmployeeDetails ed = new EmployeeDetails();
		String generatedFileName = null;
		boolean changesMade = false;
		
		if (file.length() != 0) {
			if (changesMade) {
				int returnVal = JOptionPane.showOptionDialog(ed, "Do you want to save changes?", "Save",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				// if user chooses to save file, save file
				if (returnVal == JOptionPane.YES_OPTION) {
					ed.saveFile();
					// delete generated file if user saved details to other file
					if (file.getName().equals(generatedFileName))
						file.delete();
					System.exit(0);
				} 
					
				else if (returnVal == JOptionPane.NO_OPTION) {
					if (file.getName().equals(generatedFileName))
						file.delete();
					System.exit(0);
				}  
			} 
			else {
				if (file.getName().equals(generatedFileName))
					file.delete(); 
				System.exit(0); 
			} 
				
		} else {
			if (file.getName().equals(generatedFileName))
				file.delete();
			System.exit(0);
		}	
}
}
