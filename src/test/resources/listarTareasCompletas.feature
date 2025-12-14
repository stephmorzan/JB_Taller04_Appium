Feature: List Tasks

  Background: Preparar lista de tareas
    Given que he creado una lista con 12 tareas
    And la lista de tareas esta visible en la pantalla
    And scrolleo al final de la lista

  Scenario: Verificar la Tarea Marcada en la Lista de Completadas
    When marco la ultima tarea creada como completada
    And accedo a la seccion "Completed Tasks"
    Then solo deberia ver una tarea en la lista de tareas completadas
