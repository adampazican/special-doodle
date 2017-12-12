package sk.pazican.adam.painter;

/**
 * Funkcionalny interface obsahujúci len 1 metódu.
 * Tento interface nam dovoľuje vytvárať lambda funkcie a ďalej ich posúvať funkciám.
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
@FunctionalInterface
interface ILineDrawingMethod {
    void drawLine(int x, int y, int width, int height);
}