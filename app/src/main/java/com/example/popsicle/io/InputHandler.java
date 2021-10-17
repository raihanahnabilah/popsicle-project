package com.example.popsicle.io;

import com.example.popsicle.models.CharacterPosition;

public class InputHandler implements InputListener.Callback{
    private ClickAction onClickAction;

    public void setOnClickAction(ClickAction onClickAction) {
        this.onClickAction = onClickAction;
    }

    @Override
    public void onClick(CharacterPosition pos) {
        if (onClickAction != null) onClickAction.execute(pos);
    }

}
