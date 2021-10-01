<center>

# SpaceSentinal
#### Short summary of this plugin.
This Plugin will be in the future a kind of Essentinals
You can integrate your own economy system. And your own Services. Over the api you have the chance to register and use your own services
</center>

<details>
<summary>Getting the API</summary>

```java
    SpaceSentinalAPI = SpaceSentinalAPI.getApi();
```
</details>
<br>

<center> 

# Register your own Economy

</center>

<details>
<summary>Step 1 of 3</summary>

First you have to implement IEconomy to your class

```java
import de.spaceai.spacesentinal.api.economy.IEconomy;

class MyEconomyClass implements IEconomy {
    
}
```

</details>

<details>
<summary>Step 2 of 3</summary>

Then Implement all Methods from IEconomy and give them her functionality

```java
import de.spaceai.spacesentinal.api.economy.IEconomy;
import de.spaceai.spacesentinal.api.economy.user.IEconomyUser;
import de.spaceai.spacesentinal.service.economy.EconomyUser;

import java.util.List;
import java.util.UUID;

class MyEconomyClass implements IEconomy {
    @Override
    public List<IEconomyUser> getEconomyUsers() {
        return null;
    }

    @Override
    public void loadUser(UUID uuid) {

    }

    @Override
    public void removeUser(UUID uuid) {

    }

    @Override
    public boolean existUser(UUID uuid) {
        return false;
    }

    @Override
    public EconomyUser getUser(UUID uuid) {
        return null;
    }

    @Override
    public EconomyUser getUser(String name) {
        return null;
    }
}
```

</details>

<details>
<summary>Step 3 of 3</summary>

For the Last Step register the Economy System

```java
    EconomyService economyService = SpaceSentinalAPI.getAPI().getService("economyService");
    economyService.registerEconomy(YourEconomyObject);
```

</details>

<br>

# Register your own Service

<details>
<summary>Step 1 of 2</summary>

First extends Service and create the constructor

````java
import de.spaceai.spacesentinal.service.Service;

class YourService extends Service {
    
    public YourService(String serviceName) {
        super(serviceName);
    }
    
}
````

</details>

<details>
<summary>Step 2 of 2</summary>

And last but not at least register Your Service

````java
SpaceSentinalAPI.getAPI().getServiceManager().registerService(YourServiceObject);
````

</details>
