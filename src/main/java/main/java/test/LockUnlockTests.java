package main.java.test;


import main.java.service.ContractService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LockUnlockTests {

    //@Autowired
    ContractService contractService = new ContractService();

    @Test
    public void adminLockTest(){

        Assert.assertTrue(
                "Contract was successfully locked"
                .equals( contractService.adminLock( "2", "lock" ))
        );
    }

    @Test
    public void adminUnLockTest(){

        Assert.assertTrue(
                "Contract was successfully unlocked"
                .equals( contractService.adminLock( "2", "unlock" ))
        );
    }

    @Test
    public void userLockTest(){

        Assert.assertTrue(
                "Your contract was successfully locked"
                .equals( contractService.userLock( "2", "lock" ))
        );
    }

    @Test
    public void userUnLockTest(){

        Assert.assertTrue(
                "Your contract was successfully unlocked"
                .equals( contractService.userLock( "2", "unlock" ))
        );
    }
}
