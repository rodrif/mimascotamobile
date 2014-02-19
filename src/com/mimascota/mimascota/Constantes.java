package com.mimascota.mimascota;


public class Constantes {
	static String IPSERVER = "192.168.1.35";
	static int viewerWidth = 256; //Para achicar la imagen que se ve en el celular.
	static int viewerHeight = 179; //Para achicar la imagen que se ve en el celular.
	static int webWidth = 270; //Para achicar la imagen a mandar al server.
	static int webHeight = 190; //Para achicar la imagen a mandar al server.
	static boolean debug = false; //sirve para saltear protecciones de llenado de formulario, foto y ubicacion
	static boolean saltearLogin = false;
	public static void setIpServer(String string) {
		IPSERVER = string;		
	}
}
