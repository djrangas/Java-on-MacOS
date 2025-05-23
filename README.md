## 1. Setup
To verify your Java installation, run:

```sh
java -version
```

To install OpenJDK with Homebrew:

```sh
brew install openjdk
```

Add OpenJDK to your `PATH` (for zsh):

```sh
echo 'export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### Troubleshooting

If Java is installed but not detected, check the installation path:

```sh
/usr/libexec/java_home
```

If a path is returned, set the `JAVA_HOME` variable:

```sh
export JAVA_HOME=$(/usr/libexec/java_home)
echo 'export JAVA_HOME=$(/usr/libexec/java_home)' >> ~/.zshrc
source ~/.zshrc
```

### Running a Java Program

Create a file named `Main.java`:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:

```sh
javac Main.java
java Main
```

Or run directly (Java 11+):

```sh
java Main.java
```

### Sample Output

```
Hello, World!
```

---

## 2. Using External JARs (e.g., MySQL Connector)

```sql
CREATE DATABASE djrangas_db;
SHOW DATABASES;
```

To compile and run with external JARs (replace `MY_JARS` with your jars directory):

```sh
javac -cp ".:MY_JARS/*" Main.java
java -cp ".:MY_JARS/*" Main
```

For example, with MySQL Connector/.jar:

```sh
javac -cp ".:mysql-connector-j-9.2.0/*" DatabaseTest.java
java -cp ".:mysql-connector-j-9.2.0/*" DatabaseTest
```
### Sample Output

```
Connected to the database.
```
---

### 3. MySQL CRUD Example with table

```sql
USE djrangas_db;

CREATE TABLE users(
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100)
);
```

To compile:

```sh
javac -cp ".:mysql-connector-j-9.2.0/*" MySQLCrudExample.java
java -cp ".:mysql-connector-j-9.2.0/*" MySQLCrudExample
```

### Sample Output

```
Connected to the database.
User created successfully.
User created successfully.
ID: 1, Name: John Doe
ID: 2, Name: Jane Smith
User updated successfully.
ID: 1, Name: Rangas
ID: 2, Name: Jane Smith
User deleted successfully.
ID: 1, Name: Rangas
Connection closed.
```

### Checking the MySQL CLI

```sh
mysql -u root
```

Example MySQL session:

```sql
use djrangas_db;
select * from users;
```

Sample output:

```
+----+--------+
| id | name   |
+----+--------+
| 1  | Rangas |
+----+--------+
1 row in set
```