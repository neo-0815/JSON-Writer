package me.neo_0815.json.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSON_FileManager {

	private static void writeText(String text, BufferedWriter writer) {
		try {
			writer.write(text);
			writer.newLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void createItemFile(String modid, String name, File file) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		writeItemCode(modid, name, file);
	}

	public static void createBlockFile(String modid, String name, File file, File file2, File file3) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(!file2.exists()) {
			try {
				file2.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		writeBlockCode(modid, name, file, file2, file3);
	}

	private static void writeItemCode(String modid, String name, File file) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fw);

			writeText("{", writer);
			writeText("    \"parent\": \"item/generated\",", writer);
			writeText("    \"textures\": {", writer);
			writeText("        \"layer0\": \"" + modid + ":items/" + name + "\"", writer);
			writeText("    }", writer);
			writeText("}", writer);

			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeBlockCode(String modid, String name, File file, File file2, File file3) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fw);

			writeText("{", writer);
			writeText("    \"parent\": \"" + modid + ":block/" + name + "\",", writer);
			writeText("    \"display\": {", writer);
			writeText("        \"thirdperson\": {", writer);
			writeText("            \"rotation\": [ 10, -45, 170 ],", writer);
			writeText("            \"translation\": [ 0, 1.5, -2.75 ],", writer);
			writeText("            \"scale\": [ 0.375, 0.375, 0.375 ]", writer);
			writeText("        }", writer);
			writeText("    }", writer);
			writeText("}", writer);

			FileWriter fw2 = new FileWriter(file2);
			BufferedWriter writer2 = new BufferedWriter(fw2);

			writeText("{", writer2);
			writeText("    \"parent\": \"block/cube_all\",", writer2);
			writeText("    \"textures\": {", writer2);
			writeText("        \"all\": \"" + modid + ":blocks/" + name + "\"", writer2);
			writeText("    }", writer2);
			writeText("}", writer2);

			FileWriter fw3 = new FileWriter(file3);
			BufferedWriter writer3 = new BufferedWriter(fw3);

			writeText("{", writer3);
			writeText("    \"variants\": {", writer3);
			writeText("        \"normal\": { \"model\": \"" + modid + ":" + name + "\" }", writer3);
			writeText("    }", writer3);
			writeText("}", writer3);

			writer.close();
			writer2.close();
			writer3.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
