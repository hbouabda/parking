package com.example.parking.repository;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
                "classpath:datasets/actionplanning/create_action_planning.sql"
        }),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
                "classpath:datasets/actionplanning/delete_action_planning.sql"
        })
})
public class ActionPlanningRepositoryIT {

    /*public static final int PROGRAM_ID_1 = 1;

    @Autowired
    private ActionPlanningRepository actionPlanningRepository;

    @Test
    public void findActionsPlanning() {

        LocalDate startDate = LocalDate.of(2020, 05, 21);
        LocalDate endDate = LocalDate.of(20, 05, 27);

        //given
        ActionPlanningListItem actionlistItem1 = new ActionPlanningListItem(1,
                1,
                startDate,
                true,
                startDate,
                endDate,
                1,
                1);

        ActionPlanningListItem actionlistItem2 = new ActionPlanningListItem(2,
                2,
                startDate,
                true,
                startDate,
                endDate,
                1,
                1);


        //when
        List<ActionPlanningListItem> result = actionPlanningRepository.getActionsForPlanning(PROGRAM_ID_1);

        //then
        assertThat(result).usingElementComparatorIgnoringFields("startDate", "groupStartDate", "groupEndDate").containsExactlyInAnyOrder(actionlistItem1, actionlistItem2);
    }*/
}

