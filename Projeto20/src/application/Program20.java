package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program20 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println("Entre com o caminho completo do arquivo: ");
		String path = sc.nextLine();
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path));){
			
			List<Funcionario> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Funcionario(fields [0] ,fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.print("Entre com o salario: ");
			double salary = sc.nextDouble();
			System.out.println();
			
			List<String> emails = list.stream().filter(x -> x.getSalary() > salary).map(x -> x.getEmail()).sorted().collect(Collectors.toList());
			System.out.println("Email das pessoas com o salario a cima de $" + String.format("%.2f", salary) + ":");
			emails.forEach(System.out::println);
			System.out.println();
			
			double sum = list.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0, (x, y) -> x + y);
			System.out.println("A soma das pessoas com a inicial 'M' é de: " + String.format("%.2f", sum));
		} 
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();

	}

}
