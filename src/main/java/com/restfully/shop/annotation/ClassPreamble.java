/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfully.shop.annotation;

import java.lang.annotation.Documented;

/**
 *
 * @author AG BRIGHTER
 */
@Documented
public @interface ClassPreamble
{
    String author();
   String date();
   int currentRevision() default 1;
   String lastModified() default "N/A";
   String lastModifiedBy() default "N/A";
   // Note use of array
   String[] reviewers();
}
