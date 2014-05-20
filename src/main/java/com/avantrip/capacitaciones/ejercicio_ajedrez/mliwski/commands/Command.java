package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;

public abstract class Command {
	public abstract List<Notification> ejecutar();
}
