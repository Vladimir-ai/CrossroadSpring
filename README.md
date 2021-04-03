# CrossroadSpring

## Условие задачи

**Моделирование движения на перекрестке дорог**

  На перекрестке двух автомобильных дорог расположены регулирующие движение светофоры. Каждая из дорог содержит несколько полос (рядов), автомобили двигаются в обоих направлениях. Светофоры обеспечивают проезд автомобилей по обеим дорогам, включая левый и правый повороты автомобилей, а также переход через эти дороги пешеходов.

  Программа моделирования и визуализации движения на таком перекрестке служит для исследования характера возникающих на перекрестке автомобильных дорог заторов и их рассасывания в зависимости от плотностей потоков автомобилей и режимов работы светофоров.
    
  Автомобили должны появляться на концах каждой из дорог случайным
образом, проезжать по ним со скоростью, заданной при их появлении,
притормаживая и останавливаясь при необходимости на перекрестке, и исчезая после проезда всей дороги на ее противоположном конце. У каждого автомобиля может быть своя начальная скорость, она определятся как случайная величина из некоторого диапазона (например, от 30 до 120 км/час). Случайной величиной
является также интервал между появлениями автомобилей на каждой дороге – от диапазона изменения этой величины (и закона ее распределения) зависит плотность потока автомобилей. Как случайную величину, определяемую в момент появления автомобиля на дороге, следует моделировать и направление его проезда через перекресток (прямо / налево / направо).

  Автомобили должны перестраиваться из одного ряда в другой и
пересекать перекресток в соответствии с правилами дорожного движения. В
частности, в левый ряд перед светофором становятся автомобили, которым
необходим поворот налево. Кроме правил смены полосы, в программе должны
быть зафиксированы законы торможения и ускорения автомобилей на
перекрестке, которые в общем случае зависят от допустимого сближения между
автомобилями, величин их скорости и др. Возможность аварий (например, из-за
нарушений правил дорожного движения) в модели можно не учитывать.

  Цель проводимого моделирования – изучение различных режимов работы
светофоров для поиска режима их оптимальной работы. Следует рассмотреть
два типа режимов работы: статический, когда интервалы свечения каждого цвета
(желтый, зеленый, красный) зафиксированы заранее, и динамический, при
котором интервалы свечения изменяются в соответствии с количеством
автомобилей (и пешеходов), ожидающих проезда (прохода) через дорогу.

  В изменяемые параметры моделирования движения следует включить:
тип режима работы светофора, интервалы свечения каждого цвета (для
статического режима), дистанцию видимости светофора, диапазон возможных
скоростей автомобилей, интервалы случайного появления автомобилей на
каждой из дорог.

  Визуальная картина движения на перекрестке дорог должна содержать
изображения дорог, светофоров, движущихся машин. Полезно отобразить тем
или иным образом (например, разными цветами) возможные направления
движения автомобиля через перекресток (прямо/налево/направо). Желательно
также предусмотреть вывод некоторых подсчитанных в ходе моделирования
величин, к примеру, среднее время остановки автомобилей на перекрестке.
