class Observable {
    private List<Observer> observers = new ArrayList<>();

    // Método para adicionar observadores
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Método para remover observadores
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Método para notificar todos os observadores
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
