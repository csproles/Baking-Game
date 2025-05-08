package com.cakegame.model;

public void startNewOrder(CustomerOrder order, int timeLimit) {
    this.currentOrder = order;
    inventory.clear();
    currentMix = null;
    currentBakedCake = null;
    currentDecoratedCake = null;
    currentBox = null;

    gameTimer = new GameTimer(timeLimit, this::onTimeUp);
    gameTimer.start();
}

