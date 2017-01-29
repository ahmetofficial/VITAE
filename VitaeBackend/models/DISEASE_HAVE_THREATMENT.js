/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DISEASE_HAVE_THREATMENT', {
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DISEASES',
        key: 'disease_id'
      }
    },
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'TREATMENTS',
        key: 'treatment_id'
      }
    }
  }, {
    tableName: 'DISEASE_HAVE_THREATMENT'
  });
};
