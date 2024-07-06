package com.ruvianfabrin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o caminho da pasta onde estão as imagens:");

        String sourceFolder = scanner.nextLine();; // Caminho da pasta de origem passado como argumento
        String destinationFolder = sourceFolder + "/convertido"; // Pasta de destino para as imagens convertidas
        File dir = new File(sourceFolder);
        File[] directoryListing = dir.listFiles();
        new File(destinationFolder).mkdirs(); // Cria a pasta de destino se não existir

        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Verifica se é uma imagem jpg ou png
                if (child.getName().endsWith(".jpg") || child.getName().endsWith(".png") || child.getName().endsWith(".jpeg")) {
                    try {
                        // Lê a imagem
                        BufferedImage originalImage = ImageIO.read(child);
                        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                        // Calcula a nova altura mantendo a proporção
                        int width = originalImage.getWidth();
                        int height = originalImage.getHeight();
                        int newWidth = 700;
                        int newHeight = (newWidth * height) / width;

                        // Redimensiona a imagem
                        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
                        Graphics2D g = resizedImage.createGraphics();
                        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                        g.dispose();

                        // Salva a imagem convertida na pasta de destino
                        String formatName = "jpg";
                        ImageIO.write(resizedImage, formatName, new File(destinationFolder + "/" + child.getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("A pasta fornecida não existe ou não é um diretório.");
        }
    }
}
