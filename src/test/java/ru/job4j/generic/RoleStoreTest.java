package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRolenameIsBoss() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Boss"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        Role result = store.findById("5");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsBoss() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.add(new Role("1", "Slave"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Boss"));
    }

    @Test
    public void whenReplaceThenRolenameIsSlave() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.replace("1", new Role("1", "Slave"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Slave"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.replace("5", new Role("5", "Slave"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Boss"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsBoss() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.delete("5");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Boss"));
    }
}