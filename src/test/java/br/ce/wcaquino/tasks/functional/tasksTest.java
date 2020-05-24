package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class tasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
	
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
	
			//escrever a data
			driver.findElement(By.id("duedate")).sendKeys("10/10/2020");
			
			//clicar no botão salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
		} finally {
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
	

			//escrever a data
			driver.findElement(By.id("duedate")).sendKeys("10/10/2020");
			
			//clicar no botão salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
	
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
	
		
			//clicar no botão salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
		
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
	
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
	
			//escrever a data
			driver.findElement(By.id("duedate")).sendKeys("10/10/2010");
			
			//clicar no botão salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
		//fechar o browser
		driver.quit();
		}
	}
}
