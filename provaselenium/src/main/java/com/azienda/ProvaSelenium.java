import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProvaSelenium {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("http://192.168.1.210/#/login");

            // Login
            WebElement selectUser = wait.until(ExpectedConditions.elementToBeClickable(By.id(":r0:")));
            selectUser.click();
            Thread.sleep(3000);
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class,'MuiMenuItem-root') and contains(text(),'Roberto')]")
            ));
            option.click();
            Thread.sleep(2000);
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(":r2:")));
            passwordField.sendKeys("secret");
            Thread.sleep(1000);
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Log in')]")
            ));
            loginButton.click();
            Thread.sleep(5000);

            // Navigate Tools > User Manager
            WebElement toolsButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//p[contains(text(),'TOOLS')]")
            ));
            toolsButton.click();
            Thread.sleep(3000);
            WebElement userManagerButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//p[contains(text(),'USER MANAGER')]")
            ));
            userManagerButton.click();
            Thread.sleep(5000);

            // Switch iframe if present
            try {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
                driver.switchTo().frame(iframe);
                System.out.println("✅ Switchato su iframe prima di cliccare Utenti");
            } catch (Exception e) {
                System.out.println("ℹ️ Nessun iframe rilevato prima di cliccare Utenti");
            }

            // Click "Utenti"
            WebElement utentiButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'MuiPaper-root') and .//span[contains(text(),'Utenti')]]")
            ));
            utentiButton.click();
            Thread.sleep(5000);

            // === INIZIO CONTROLLO ED EVENTUALE ELIMINAZIONE ===

            boolean utenteEsiste = false;
            String nomeDaCercare = "Luca";
            String cognomeDaCercare = "Bello";

            while (true) {
                List<WebElement> righe = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

                for (WebElement riga : righe) {
                    String nome = riga.findElement(By.xpath(".//div[@data-field='name']")).getText().trim();
                    String cognome = riga.findElement(By.xpath(".//div[@data-field='surname']")).getText().trim();

                    if (nome.equalsIgnoreCase(nomeDaCercare) && cognome.equalsIgnoreCase(cognomeDaCercare)) {
                        System.out.println("❌ L'utente " + nome + " " + cognome + " ESISTE già! Procedo con eliminazione.");

                        // Scrolla la riga in vista
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", riga);
                        Thread.sleep(500);

                        // Trova il bottone elimina nella riga
                        List<WebElement> bottoni = riga.findElements(By.xpath(".//button[contains(@class,'MuiIconButton-root')]"));
                        boolean trovato = false;
                        for (WebElement bottone : bottoni) {
                            String innerHTML = bottone.getAttribute("innerHTML");
                            if (innerHTML != null && innerHTML.contains("DeleteIcon")) {
                                bottone.click();
                                trovato = true;
                                Thread.sleep(1000);
                                break;
                            }
                        }

                        if (!trovato) {
                            System.out.println("❌ Bottone Elimina non trovato nella riga.");
                            break;
                        }

                        // Conferma eliminazione
                        try {
                            WebElement conferma = wait.until(ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[contains(.,'Conferma')]")));
                            conferma.click();
                            Thread.sleep(1000);
                            System.out.println("🗑️ Utente eliminato.");
                        } catch (Exception e) {
                            System.out.println("⚠️ Nessun popup di conferma trovato.");
                        }

                        utenteEsiste = true;
                        break;
                    }
                }

                if (utenteEsiste) break;

                // Vai alla prossima pagina
                WebElement nextPageButton = driver.findElement(By.xpath("//button[@aria-label='Vai alla pagina successiva']"));
                String disabledAttr = nextPageButton.getAttribute("disabled");
                if (disabledAttr != null && (disabledAttr.equals("true") || disabledAttr.equals(""))) {
                    break;
                } else {
                    nextPageButton.click();
                    Thread.sleep(2000);
                }
            }

            // === CREAZIONE UTENTE (sempre) ===

            WebElement aggiungiUtente = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'primary') and .//img]")));
            aggiungiUtente.click();
            Thread.sleep(3000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(nomeDaCercare);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("surname"))).sendKeys(cognomeDaCercare);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("lbello");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("M4r!0R0ss1!2027");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("emailprova12@gmail.com");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobile"))).sendKeys("5432099583");

            WebElement gruppi = wait.until(ExpectedConditions.elementToBeClickable(By.id("groupId")));
            gruppi.click();
            Thread.sleep(1000);
            WebElement gruppiOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(),'Software - HMI')]")));
            gruppiOption.click();
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).click();
            Thread.sleep(500);

            WebElement ruolo = wait.until(ExpectedConditions.elementToBeClickable(By.id("roleId")));
            ruolo.click();
            Thread.sleep(1000);
            WebElement ruoloOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(),'Utente')]")));
            ruoloOption.click();
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).click();
            Thread.sleep(500);

            WebElement azienda = wait.until(ExpectedConditions.elementToBeClickable(By.id("organizationId")));
            azienda.click();
            Thread.sleep(1000);
            WebElement aziendaOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(),'Graphimecc')]")));
            aziendaOption.click();
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).click();
            Thread.sleep(500);

            WebElement confermaButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'primary') and @type='submit']")));
            confermaButton.click();

            if (utenteEsiste) {
                System.out.println("🟢 Utente ricreato con successo.");
            } else {
                System.out.println("🟢 Utente creato con successo.");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // opzionale
        }
    }
}