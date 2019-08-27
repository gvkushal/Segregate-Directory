package com.practice.BinarySearchTree;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderFiles {

	public static void main(String[] args) {
		String path = "C:/Users/gvkushal/BackUp/D-Drive/OIA/";
		File fileObj = new File(path);
		File[] files = fileObj.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		});
		if (segregateFiles(files, fileObj))
			System.out.println("\b Task Sucessful");
		else
			System.out.println("Test couldnot be complete because of some exception");
	}

	private static boolean segregateFiles(File[] files, File fileObj) {
		try {
			String path = fileObj.getAbsolutePath();
			for (File file : files) {
				File dir = new File(path + "/" + getExtension(file.getName()).toLowerCase());
				Path source = FileSystems.getDefault().getPath(file.getAbsolutePath());
				Path target = FileSystems.getDefault().getPath(dir.getAbsolutePath() + "/" + file.getName());
				if (dir.exists()) {
					Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
				} else {
					dir.mkdir();
					Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getSuppressed());
			return false;
		}

	}

	private static String getExtension(String file) {
		return file.substring(file.lastIndexOf(".") + 1);
	}

}
