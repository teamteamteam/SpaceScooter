package de.teamteamteam.spacescooter.utility;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This helper is used by the Loader to get a list of files of the whole Code
 * and determine specific runtime configurations.
 * Since there are some differences between OS and jar/non-jar environments,
 * this helper makes sure, everything still works.
 */
public class CodeEnvironment {

	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private CodeEnvironment() {}
	
	
	/**
	 * Returns true if the program is run out of a jar.
	 */
	public static boolean isJar() {
		return (CodeEnvironment.class.getProtectionDomain().getCodeSource().getLocation().toString().endsWith("jar"));
	}
	
	/**
	 * Returns a String[] containing relative paths to files within the code.
	 */
	public static String[] getFileList() {
		URL codeLocation = CodeEnvironment.class.getProtectionDomain().getCodeSource().getLocation();
		if(CodeEnvironment.isJar()) {
			return CodeEnvironment.getFileListFromJar(codeLocation);
		} else {
			File codeFolder = null;
			try {
				codeFolder = new File(codeLocation.toURI());
			} catch (URISyntaxException e) {
				System.err.println("Could not convert codeLocation URL to File!");
				e.printStackTrace();
			}
			return CodeEnvironment.getFileListFromFolder(codeFolder);
		}
	}
	
	/**
	 * Return a relative path list of files based on a given folder.
	 */
	private static String[] getFileListFromFolder(File folder) {
		ArrayList<String> fileList = new ArrayList<String>();
		String rootPath = folder.getAbsolutePath() + File.separator;
		String[] folderContents = CodeEnvironment.getAbsoluteFileListFromFolder(folder);
		for(String element : folderContents) {
			fileList.add(element.replace(rootPath, ""));
		}
		return fileList.toArray(new String[fileList.size()]);
	}
	
	/**
	 * Recursively generate an absolute path list of elements within a folder.
	 */
	private static String[] getAbsoluteFileListFromFolder(File folder) {
		ArrayList<String> fileList = new ArrayList<String>();
		File[] folderContents = folder.listFiles();
		for(File f : folderContents) {
			if(f.isDirectory()) {
				String[] filesInDirectory = CodeEnvironment.getAbsoluteFileListFromFolder(f);
				for(String entry : filesInDirectory) {
					fileList.add(entry);
				}
			} else {
				fileList.add(f.toString());
			}
		}
		return fileList.toArray(new String[fileList.size()]);
	}
	
	/**
	 * Returns a relative path list of files that are contained within the current jar.
	 */
	private static String[] getFileListFromJar(URL jar) {
		List<String> list = new ArrayList<String>();
		ZipInputStream zip = null;
		try {
			zip = new ZipInputStream(jar.openStream());
		} catch (IOException e) {
			System.err.println("Could not open jar file!");
			e.printStackTrace();
		}
		ZipEntry ze = null;
		try {
			while ((ze = zip.getNextEntry()) != null) {
				String entryName = ze.getName();
				list.add(entryName);
			}
		} catch (IOException e) {
			System.err.println("Error reading jar contents!");
			e.printStackTrace();
		}
		return list.toArray(new String[list.size()]);
	}
	
}
