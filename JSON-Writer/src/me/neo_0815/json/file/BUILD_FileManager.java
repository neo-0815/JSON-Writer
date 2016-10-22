package me.neo_0815.json.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BUILD_FileManager {

	public static void setModID(String path, String modid) {
		File file = new File(path + "build.neo_0815");

		try {
			FileWriter fw = new FileWriter(file);

			fw.write(modid);
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static String getModID(String path) {
		String modid = "";
		File file = new File(path + "build.neo_0815");

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);

			modid = reader.readLine();
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}

		return modid;
	}
}
